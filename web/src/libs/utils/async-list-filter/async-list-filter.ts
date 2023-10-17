/**
 * Filters any provided list asynchronously.
 *
 * Source: https://stackoverflow.com/a/33362966
 */
export class AsyncListFilter<T> {
  constructor(private readonly list: T[]) {
  }

  static from<S>(list: S[]) {
    return new AsyncListFilter(list);
  }

  async filter(predicate: (item: T, index: number, list: T[]) => Promise<boolean>) {
    try {
      const list = [...this.list];
      const result = await Promise.all(list.map((item, index) => predicate(item, index, list)));
      return list.filter((item, index) => result[index]);
    } catch (e) {
      return [];
    }
  }

}
