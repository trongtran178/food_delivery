package hcmute.spkt.tranngoctrong.food_delivery.services;


public class Api {

    private static Api instance;

    private Api() { }

    public static Api getInstance() {
        if (instance != null) {
            instance = new Api();
        }
        return instance;
    }
}
