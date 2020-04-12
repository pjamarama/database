package mvc.view;

import mvc.controller.View;

public class MessageView implements View {
    private String message;

    public MessageView(String message) {
        this.message = message;
    }

    @Override
    public void view() {
        System.out.println(message);
    }
}
