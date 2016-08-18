package com.oculussoftware.api.sysi;

/**
 * Provides a high-level API into the underlying facilities.
 *
 * @author Jim Coles
 */
public interface IEnvironment {

    void getTempDir();

    void getCurrentWorkingDir();

    void setCurrentWorkingDir();
}