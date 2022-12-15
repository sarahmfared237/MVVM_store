package view;

import java.util.ArrayList;

public class FramesManger {
    ArrayList<NavigationObserver> navigationObserverList = new ArrayList<>();

    public void displayFrame(NavigationObserver navigationObserver) {
        if (navigationObserverList.size() > 0)
            navigationObserverList.get(navigationObserverList.size() - 1).hideFrame();
        navigationObserverList.add(navigationObserver);
        navigationObserver.openFrame();
    }

    public void closeFrame() {
        navigationObserverList.remove(navigationObserverList.size() - 1).closeFrame();

        if (navigationObserverList.size() > 0) {
            navigationObserverList.get(navigationObserverList.size() - 1).openFrame();
        }
    }
}
