package pl.grm.ex.inputs;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import pl.grm.ex.Example1;
import pl.grm.ex.entities.RedSquare;

public class DefaultListeners {
	private static HashMap<Integer, GameKeyListener> defListeners;

	private static void init() {
		defListeners = new HashMap<Integer, GameKeyListener>();
		defListeners.put(Keyboard.KEY_ESCAPE, ESC_Listener());
		defListeners.put(Keyboard.KEY_R, R_Listener());
	}

	public static boolean contains(int value) {
		if (defListeners == null) {
			init();
		}
		return defListeners.containsKey(value);
	}

	public static GameKeyListener getListener(int value) {
		if (defListeners == null) {
			init();
		}
		return defListeners.get(value);
	}

	private static GameKeyListener ESC_Listener() {
		return new GameKeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (Example1.instance.getGameStage()) {
				case MAIN:
					Example1.stop();
					break;
				default:
					break;
				}
			}
		};
	}

	private static GameKeyListener R_Listener() {
		return new GameKeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				Example1.addEntity(new RedSquare());
			}
		};
	}

	public static Map<Integer, ? extends GameKeyListener> getAll() {
		if (defListeners == null) {
			init();
		}
		return defListeners;
	}

}