package cz.educanet.webik;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class LoginManager implements Serializable {
    User loggers = null;
}
