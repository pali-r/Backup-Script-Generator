package palir.TUI.Views;

import palir.TUI.ActionHandler;

public class ConfigureFlagsView implements View {

    @Override
    public void print() {
        System.out.println("----------------------------------------------");
        System.out.println("c -> configure r[C]lone flags");
        System.out.println("s -> configure r[S]ync flags");
        System.out.println("x -> go back");

        ActionHandler.handleAction(this);
    }

}
