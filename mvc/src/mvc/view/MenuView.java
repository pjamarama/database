/**
 * All user interface is here
 */

package mvc.view;

import mvc.controller.View;

public class MenuView implements View {



    @Override
    public void view() {
        System.out.println(" ");
        System.out.println("list - Вывести всех студентов на экран");
        System.out.println("add - Добавить нового студента");
        System.out.println("quit - Выход");
    }
}
