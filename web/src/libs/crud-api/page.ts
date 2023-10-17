import { PageEvent } from '@angular/material/paginator';

export class Page<T> {
  /**
   * Lazy initialized {@link PageEvent} data, will generate when accessed.
   */
  private pageEvent: PageEvent | undefined;

  /**
   * Lazy initialized {@link PageReq} data.
   */
  private nextPageReq: PageReq | undefined;

  /**
   * Lazy initialized {@link PageReq} data.
   */
  private previousPageReq: PageReq | undefined;

  constructor(
    readonly content: T[],
    readonly details: Readonly<PageDetails>,
    readonly sort: string[] | undefined,
  ) {
  }

  /**
   * Creates a next page request based on the provided details.
   */
  next(): PageReq {
    if (!this.nextPageReq) {
      const { size, number, first, last } = this.details;
      const unpaged = first && last;
      const sort = this.sort != null ? [...this.sort] : undefined;
      this.nextPageReq = unpaged
        ? { sort, unpaged, }
        : { sort, size, page: !last ? (number + 1) : number, };
    }
    return this.nextPageReq;
  }

  /**
   * Creates a previous page request based on the provided details.
   */
  previousOrFirst(): PageReq {
    if (!this.previousPageReq) {
      const { size, number, first, last } = this.details;
      const unpaged = first && last;
      const sort = this.sort != null ? [...this.sort] : undefined;
      this.previousPageReq = unpaged
        ? { sort, unpaged, }
        : { sort, size, page: !first ? (number - 1) : 0, };
    }
    return this.previousPageReq;
  }

  /**
   * Returns {@link PageDetails} data as a {@link PageEvent}
   */
  get event(): PageEvent {
    if (!this.pageEvent) {
      const { size, number, totalElements } = this.details;
      this.pageEvent = {
        pageSize: size,
        // Angular page index starts at 1
        pageIndex: number,
        // Previous page is the same as details index
        previousPageIndex: number > -1 ? number : undefined,
        // Number of total items
        length: totalElements,
      };
    }
    return this.pageEvent;
  }

  isUnpaged() {
    const { first, last, size, totalElements } = this.details;
    return first && last && size === totalElements;
  }

  public static unpaged<S>(content?: S[] | undefined, sort?: string[] | undefined): Page<S> {
    const tempContent = content || [];
    const details: PageDetails = {
      first: true,
      last: true,
      size: tempContent.length,
      number: 0,
      totalElements: tempContent.length,
      totalPages: 1,
    };
    return new Page<S>([], details, sort);
  }

  public static of<S>(content: S[], details: Partial<PageDetails>, sort?: string[] | undefined) {
    const tempDetails: PageDetails = {
      first: details.first || false,
      last: details.last || false,
      number: details.number || 0,
      size: details.size || content.length,
      totalElements: details.totalElements || content.length,
      totalPages: details.totalPages || 1,
    };
    return new Page<S>(content, tempDetails, sort);
  }

  public static ofPageResponse<S>(response: PageResponse<S>, sort?: string[] | undefined) {
    const { content, first, last, number, size, totalElements, totalPages } = response;
    const details: PageDetails = { first, last, number, size, totalElements, totalPages, };
    return new Page<S>(content, details, sort);
  }
}

export enum SortDirection {
  NONE = '',
  ASC = 'ASC',
  DESC = 'DESC',
}

export class SortBuilder {
  /**
   * Creates a new sort order list from the provided array.
   *
   * @param operations Array containing sort orders formatted as an array of strings.
   *                   e.g. [['property1', 'direction'], ['property2', 'direction']]
   */
  public static fromArray(operations: string[][]) {
    return operations.map(([prop, dir]) => this.from(prop, dir as SortDirection));
  }

  /**
   * Creates a new sort order list from the provided object list.
   *
   * @param operations Array containing a definition of sort orders.
   */
  public static fromObject(operations: { property: string, direction: SortDirection }[]) {
    return operations.map(({ property, direction }) => this.from(property, direction))
  }

  /**
   * Creates a formatted sort order from the provided property and direction.
   */
  public static from(property: string, direction: SortDirection) {
    return `${property.trim()};${direction.trim()}`
  }
}

export interface PageDetails {
  /**
   * Current page size
   */
  size: number;
  /**
   * Current page index, value starts at 0
   */
  number: number;
  /**
   * Number of elements in the list
   */
  totalElements: number;
  /**
   * Total number of available pages.
   */
  totalPages: number;
  /**
   * True if first page
   */
  first: boolean;
  /**
   * True if last page
   */
  last: boolean;
}

/**
 * Api Page response
 */
export interface PageResponse<T> {
  content: T[];

  pageable: 'INSTANCE' | {
    sort: {
      empty: boolean,
      sorted: boolean,
      unsorted: boolean
    },
    paged: boolean,
    offset: number,
    pageNumber: number,
    pageSize: number,
    unpaged: boolean
  },
  totalElements: number,
  totalPages: number,
  last: boolean,
  size: number,
  number: number,
  sort: {
    empty: boolean,
    sorted: boolean,
    unsorted: boolean
  },
  numberOfElements: number,
  first: boolean,
  empty: boolean
}

/**
 * A request may be paged, unpaged, sorted or unsorted.
 */
export type PageReq = PagedReq | UnpagedReq;

export interface PagedReq {
  size: number | undefined;
  page: number | undefined;
  sort: string[] | undefined; // [field;direction]
}

export interface UnpagedReq {
  unpaged: true;
  sort: string[] | undefined; // [field;direction]
}

export class PageReqBuilder {
  public static fromEvent(ev: PageEvent, sort?: string[]): PageReq {
    return {
      page: ev.pageIndex,
      size: ev.pageSize,
      sort,
    }
  }

  public static unpaged(sort: string[][] = []): PageReq {
    return {
      unpaged: true,
      sort: SortBuilder.fromArray(sort),
    } as UnpagedReq;
  }
}
