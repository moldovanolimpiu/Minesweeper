//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {
        MinesweeperModel model = new MinesweeperModel();
        MinesweeperView view = new MinesweeperView();

        MinesweeperController controller = new MinesweeperController(model, view);
        controller.start();



    }
}