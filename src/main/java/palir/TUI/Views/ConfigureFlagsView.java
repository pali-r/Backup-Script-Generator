package palir.TUI.Views;

import palir.TUI.ActionHandler;

public class ConfigureFlagsView implements View {

    @Override
    public void print() {
        System.out.println("""
                ----------------------------------------------
                c -> configure r[C]lone flags
                s -> configure r[S]ync flags
                
                x -> go back
                """);

        ActionHandler.handleAction(this);
    }

}
