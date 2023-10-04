export class SimpleUidGenerator {
  public static readonly SEPARATOR = '_';
  static createUid(prefix: string = SimpleUidGenerator.SEPARATOR): string {
    if (!prefix.endsWith(SimpleUidGenerator.SEPARATOR)) {
      prefix += SimpleUidGenerator.SEPARATOR;
    }
    return prefix + Math.random().toString(36).slice(2, 9);
  }
}
