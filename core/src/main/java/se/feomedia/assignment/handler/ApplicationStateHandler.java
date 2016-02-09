package se.feomedia.assignment.handler;

import se.feomedia.assignment.applicationcores.AboutCore;
import se.feomedia.assignment.applicationcores.AssignmentCore;
import se.feomedia.assignment.applicationcores.ControlCore;
import se.feomedia.assignment.applicationcores.CoreInterface;
import se.feomedia.assignment.applicationcores.MenuCore;
import se.feomedia.assignment.applicationcores.SplashCore;
import se.feomedia.assignment.applicationcores.SurvivalCore;
import se.feomedia.assignment.constants.GameConstants;

/**
 * Handles the cores used for different states
 * Created by Niklas on 2015-12-08.
 */
public class ApplicationStateHandler {
    private String currentState;
    private SplashCore splashCore;
    private MenuCore menuCore;
    private AssignmentCore assignmentCore;
    private SurvivalCore survivalCore;
    private AboutCore aboutCore;
    private ControlCore controlCore;

    /**
     * Constructor creates cores
     * @param inState
     * @param imageHandler
     */
    public ApplicationStateHandler(String inState, ImageHandler imageHandler) {
        currentState = inState;
        splashCore = new SplashCore(imageHandler);
        menuCore = new MenuCore(imageHandler);
        assignmentCore = new AssignmentCore(imageHandler);
        survivalCore = new SurvivalCore(imageHandler);
        aboutCore = new AboutCore(imageHandler);
        controlCore = new ControlCore(imageHandler);
    }

    /**
     * change state
     * @param inState
     */
    public void setCurrentState (String inState) {
        currentState = inState;
    }

    /**
     * Gets current core
     * @return
     */
    public CoreInterface getCurrentCore() {
        if(currentState.equalsIgnoreCase(GameConstants.APPLICATION_SPLASH)) {
            return splashCore;
        } else if(currentState.equalsIgnoreCase(GameConstants.APPLICATION_MENU)) {
            return menuCore;
        } else if(currentState.equalsIgnoreCase(GameConstants.APPLICATION_ASSIGNMENT)) {
            return assignmentCore;
        } else if(currentState.equalsIgnoreCase(GameConstants.APPLICATION_SURVIVAL)) {
            return survivalCore;
        } else if(currentState.equalsIgnoreCase(GameConstants.APPLICATION_ABOUT)) {
            return aboutCore;
        }else if(currentState.equalsIgnoreCase(GameConstants.APPLICATION_CONTROLS)) {
            return controlCore;
        }

        return null;
    }

    /**
     * Sets new core and returns the set core
     * @param inState
     * @return
     */
    public CoreInterface setStateGetCore (String inState) {
        if(inState.equalsIgnoreCase(GameConstants.APPLICATION_SPLASH)) {
            return splashCore;
        } else if(inState.equalsIgnoreCase(GameConstants.APPLICATION_MENU)) {
            return menuCore;
        } else if(inState.equalsIgnoreCase(GameConstants.APPLICATION_ASSIGNMENT)) {
            return assignmentCore;
        } else if(inState.equalsIgnoreCase(GameConstants.APPLICATION_SURVIVAL)) {
            return survivalCore;
        } else if(inState.equalsIgnoreCase(GameConstants.APPLICATION_ABOUT)) {
            return aboutCore;
        }else if(inState.equalsIgnoreCase(GameConstants.APPLICATION_CONTROLS)) {
            return controlCore;
        }

        return null;
    }

    /**
     * compare states
     * @param otherState
     * @return
     */
    public boolean isSameState(String otherState) {
        return currentState.equalsIgnoreCase(otherState);
    }

    /**
     * dispose of all cores
     */
    public void dispose() {
        splashCore.dispose();
        menuCore.dispose();
        assignmentCore.dispose();
        survivalCore.dispose();
        aboutCore.dispose();
        controlCore.dispose();
    }
}
