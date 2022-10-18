package CS4488.Capstone.Library.FacadeInterfaces;

/**
 * The translator Access functions for the U.I.
 *
 * @version 1.0
 * @author Traae
 */
public interface TranslatorAccess {

    /**
     * @param path of the file
     * @return success/fail
     */
    public boolean translateAndLoad(String path) throws Exception;
}
