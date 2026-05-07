package service;

public interface NotificadorPush {
    /** Envia uma notificação para o dispositivo do usuário. */
    void enviar(String mensagem);
}
