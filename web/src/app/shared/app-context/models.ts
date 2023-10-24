export type ContextChangeConfig = {
    messageText: string,
    messageParams?: Record<string, unknown>;

    autoClose?: boolean;
    autoCloseInMs?: number;
};