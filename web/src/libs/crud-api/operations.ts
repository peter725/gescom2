import { HttpParamsSrc, RequestParams } from '@libs/crud-api/models';
import { Observable } from 'rxjs';

export enum HttpOperationType {
  /**
   * Creates no specific sub-operation for the CRUD service. All internal operations use the same PATH.
   */
  SIMPLE,

  /**
   * Generates default READ operations unless there is a custom operation is defined.
   */
  READ,

  /**
   * Generates default CRUD operations unless a custom operation is defined.
   */
  CRUD,
}

/**
 * Data model used to define a Base operation
 */
export interface HttpOperationDef {
  /**
   * Operation base URL path.
   */
  path: string;
  /**
   * Common hooks that will run on all sub-operations.
   */
  hooks?: Partial<OperationHooks>;
}

/**
 * Data model used to define and generate final HTTP operations
 */
export interface OperationDef extends HttpOperationDef {
  type: HttpOperationType,
  operations?: Partial<CrudOperationsDef>,
}

/**
 * Supported sub-operations definition
 */
export interface CrudOperationsDef {
  findAll: HttpOperationDef,
  findById: HttpOperationDef,
  create: HttpOperationDef,
  update: HttpOperationDef,
  changeState: HttpOperationDef,
  delete: HttpOperationDef,
}

/**
 * Operations to be executed during the lifecycle of a Rest operation.
 * Definition is TBA, current one is just temporarily
 */
export interface OperationHooks {
  afterRun: (result: any, params: any) => Observable<any>;
  beforeRun: (params: any) => Observable<any>;
}

export const isHttpOperationDef = (val: unknown): val is HttpOperationDef => {
  if (val == null && typeof val !== 'object') {
    return false;
  }
  const keys = Object.keys(val as {});
  const acceptedProps = ['path', 'hooks'];
  return keys.length === acceptedProps.length && keys.every(key => acceptedProps.includes(key));
};

export interface OperationSrc {
  path: string;
  // readonly hooks: OperationHooks; // ?
  operations: CrudOperationsDef; // ? hooks should be all defined ?
}

export type OperationsDefSrc = { [key: string]: OperationDef };

export class RestCrudOperation {
  private readonly basePath: string;
  private readonly operations: Readonly<{
    findAll: HttpOperationDef,
    findById: HttpOperationDef,
    create: HttpOperationDef,
    update: HttpOperationDef,
    changeState: HttpOperationDef,
    delete: HttpOperationDef,
  }>;

  constructor(operation: OperationSrc) {
    this.basePath = operation.path;
    this.operations = operation.operations;
  }

  public static createUrl(path: string, params: RequestParams = {}): string {
    const rawPath = (path + '');
    return Object.entries(params)
      .filter(([key, value]) => key != null && value != null)
      .reduce((acc, [key, value]) => {
        const composite = `:${ key }`;
        return acc.replace(composite, `${ value }`);
      }, rawPath);
  }

  public static from(baseUrl: string, operation: OperationDef): RestCrudOperation {
    const src: OperationSrc = {
      path: `${ baseUrl }${ operation.path }`,
      operations: {
        findAll: {
          path: '',
          hooks: operation.hooks || {}
        },
        findById: {
          path: '',
          hooks: operation.hooks || {}
        },
        create: {
          path: '',
          hooks: operation.hooks || {}
        },
        update: {
          path: '',
          hooks: operation.hooks || {}
        },
        changeState: {
          path: '',
          hooks: operation.hooks || {}
        },
        delete: {
          path: '',
          hooks: operation.hooks || {}
        },
      }
    };

    if (operation.type === HttpOperationType.SIMPLE) {
      return new RestCrudOperation(src);
    }

    const custom = operation.operations;
    if (operation.type === HttpOperationType.READ || operation.type === HttpOperationType.CRUD) {
      src.operations.findAll.path = custom?.findAll?.path || '';
      src.operations.findById.path = custom?.findById?.path || '/:id';
    }

    if (operation.type === HttpOperationType.CRUD) {
      src.operations.create.path = custom?.create?.path || '/create';
      src.operations.update.path = custom?.update?.path || '/update/:id';
      src.operations.changeState.path = custom?.changeState?.path || '/:id/switch';
      src.operations.delete.path = custom?.delete?.path || '/delete';
    }

    return new RestCrudOperation(src);
  }

  base(params?: RequestParams): HttpOperationDef {
    const rawPath = this.basePath;
    const path = RestCrudOperation.createUrl(rawPath, params);
    return {
      path,
      hooks: {},
    }
  }

  findAll(params?: RequestParams): HttpOperationDef {
    return this.makeOperation(this.operations.findAll, params);
  }

  findById(params?: RequestParams): HttpOperationDef {
    return this.makeOperation(this.operations.findById, params);
  }

  create(params?: RequestParams): HttpOperationDef {
    return this.makeOperation(this.operations.create, params);
  }

  update(params?: RequestParams): HttpOperationDef {
    return this.makeOperation(this.operations.update, params);
  }

  changeState(params?: RequestParams): HttpOperationDef {
    return this.makeOperation(this.operations.changeState, params);
  }

  delete(params?: RequestParams): HttpOperationDef {
    return this.makeOperation(this.operations.delete, params);
  }

  private makeOperation(operation: HttpOperationDef, params?: RequestParams): HttpOperationDef {
    const { path, hooks } = operation;
    const rawPath = this.basePath + path;
    const builtPath = RestCrudOperation.createUrl(rawPath, params);
    return {
      path: builtPath,
      hooks,
    };
  }
}

export class CrudOperationStorage {
  constructor(private readonly operations: Map<string, RestCrudOperation>) {
  }

  public static from(defs: { baseUrl: string, operations: OperationsDefSrc }[]) {
    const optsMap = new Map<string, RestCrudOperation>;

    defs.forEach(({ baseUrl, operations }) => {
      Object.entries(operations).forEach(([key, operation]) => {
        optsMap.set(key, RestCrudOperation.from(baseUrl, operation));
      });
    });

    return new CrudOperationStorage(optsMap);
  }

  get(name: string) {
    const operation = this.operations.get(name);
    if (!operation) {
      throw new Error(`Requested operation [${ name }] does not exist in the operations definition`);
    }
    return operation;
  }

  set(name: string, value: RestCrudOperation) {
    this.operations.set(name, value);
  }

}
