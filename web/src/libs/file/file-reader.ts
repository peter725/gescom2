export const readFileContents = async (file: Blob | File, ): Promise<string> => {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsText(file as Blob, 'UTF-8');
    reader.onload = () => {
      if (reader.result == null) {
        return reject(new Error('Provided file produced no result'));
      }

      resolve(reader.result.toString());
    };
    reader.onerror = e => reject(e);
  });
};

export const readFilesContents = async (files: FileList) => {
  const list: Promise<string>[] = [];
  for (let i = 0; i < files.length; i++) {
    const file = files.item(i);
    if (file) {
      list.push(readFileContents(file));
    }
  }
  return Promise.all(list);
};
