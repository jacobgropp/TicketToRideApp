package CommandPattern;

import java.util.List;

/**
 * Created by jakeg on 9/16/2018.
 */

public interface ICommand {
    public List<ICommand> execute();
}
