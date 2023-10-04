import { Pipe, PipeTransform } from '@angular/core';
import { extractModelPath } from './extract-model-path';


@Pipe({ name: 'extractModelPath' })
export class ExtractModelPathPipe implements PipeTransform {
  transform(value: unknown, path = ''): string {
    return extractModelPath(value, path);
  }
}
