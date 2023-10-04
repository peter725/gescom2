export interface B64EncodedFile {
  /**
   * File encoded as string.
   */
  data: string;
  /**
   * Encoded file schema prefix.
   * https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/Data_URLs
   */
  dataUrl: string;
  /**
   * Name of the file.
   */
  name: string;
  /**
   * File MIME type.
   */
  type: string;
  /**
   * File size in bytes.
   */
  size: number;
}

export interface EncodedFile {
  /**
   * Encoded file as Data_URL
   * https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/Data_URLs
   */
  data: string;
  /**
   * Desired filename
   */
  filename?: string;
  /**
   * Applied MIME type if not available in encoded.
   */
  type?: string;
}

export interface DownloadOptions {
  /**
   * Expected filename, used if defined.
   */
  filename?: string;
  /**
   * Expected MIME type, used only when it cannot be inferred automatically.
   */
  type?: string;
}
