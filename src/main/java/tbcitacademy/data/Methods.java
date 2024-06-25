package tbcitacademy.data;

import com.codeborne.selenide.*;


public class Methods {
    public static void unCheck(ElementsCollection checkboxes) {
        for (SelenideElement checkbox : checkboxes) {
            checkbox.setSelected(false);
        }
    }

    public static void check(ElementsCollection checkboxes) {
        for (SelenideElement checkbox : checkboxes) {
            checkbox.setSelected(true);
        }
    }


    public static void selectYes(SelenideElement button) {
        if (!button.isSelected())
            button.setSelected(true);

    }

    public static void main(String[] args) {
        System.out.println("mogesalmebit");
        System.out.println("kostaaa");
    }
}
