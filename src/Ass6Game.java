// ID: 205937949

import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * The class that runs the Arkanoid game.
 */
public class Ass6Game {
    /**
     * main function runs the game.
     *
     * @param args the input arguments.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        GameFlow game = new GameFlow(ar, ks, 600, 800);
        List<LevelInformation> levels = levelsFromArgs(args);
        game.runLevels(levels);
    }

    /**
     * Levels from args list.
     * return list of levelInformation from array of strings
     * @param args the args
     * @return the list
     */
    public static List<LevelInformation> levelsFromArgs(String[] args) {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        EasyLevel l1 = new EasyLevel();
        MediumLevel l2 = new MediumLevel();
        HardLevel l3 = new HardLevel();
        ExpertLevel l4 = new ExpertLevel();
        // iterates over args array
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (Integer.toString(1).equals(s)) {
                levels.add(l1);
            }
            if (Integer.toString(2).equals(s)) {
                levels.add(l2);
            }
            if (Integer.toString(3).equals(s)) {
                levels.add(l3);
            }
            if (Integer.toString(4).equals(s)) {
                levels.add(l4);
            }
        }
        return levels;
    }
}