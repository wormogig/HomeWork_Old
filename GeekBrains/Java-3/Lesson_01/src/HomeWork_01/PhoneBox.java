package HomeWork_01;

import java.util.HashMap;
import java.util.HashSet;

class PhoneBox {
    HashMap<String, HashSet<String>> hm;

    public PhoneBox() {
        this.hm = new HashMap<>();
    }

    public void add(String name, String phone) {
        HashSet<String> hs = hm.getOrDefault(name, new HashSet<>());
        hs.add(phone);
        hm.put(name, hs);
        callback.callingBack(name);
    }
    //
    // Описание интерфейса и инициализации Callback'а
    //
    interface Callback {
        void callingBack(String name);
    }

    Callback callback;

    public void registerCallBack (Callback callback) {
        this.callback = callback;
    }
    // окончание описания Callback'а

    public void findString(String name) {
        if(hm.containsKey(name)) {
            System.out.println(hm.get(name));
        } else {
            System.out.println("такой фамилии нет");
        }
    }

    public static void main(String[] args) {
        MyCallBack myCallBack = new MyCallBack();
        PhoneBox book = new PhoneBox();
        book.registerCallBack(myCallBack);
        book.add("Ivanov", "123");
        book.add("Ivanov", "124");
        book.add("Ivanov", "125");
        book.add("Petrov", "444");
        book.add("Petrov", "445");
        book.add("Petrov", "446");
        book.findString("Ivanov");
        book.findString("Petrov");
        book.findString("Petrasdovjhk");
    }

}

class MyCallBack implements PhoneBox.Callback {

    @Override
    public void callingBack(String name) {
        System.out.println("Новая запись добавлена: " + name);
    }
}
