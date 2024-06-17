package controller;

/**
 * Interface pour les controller, permet de recevoir des informations depuis un
 * autre controller
 * 
 * @param <T> Le type d'information à recevoir
 */
public interface ReceiveInfo<T> {
    /**
     * Permet de recevoir des informations
     * 
     * @param info Les informations à recevoir
     */
    public void receiveInfo(T info);
}
