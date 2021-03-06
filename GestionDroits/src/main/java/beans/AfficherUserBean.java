package beans;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import fr.marseille.projetfinal.model.User;
import fr.marseille.projetfinal.service.UserService;

@ManagedBean
@SessionScoped
public class AfficherUserBean {
    private User                           user;
    private ClassPathXmlApplicationContext context;

    private UserService                    userServiceBean;

    public AfficherUserBean() {
        this.context = new ClassPathXmlApplicationContext("application-context.xml");
        this.userServiceBean = (UserService) context.getBean("userService");
    }

    public User getUser() {
        if (null == user) {
            return getCurrentUser();
        }

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @PostConstruct public User init() { if (currentUser == null) { Map<String, String> requestMap =
     *                FacesContext.getCurrentInstance().getExternalContext() .getRequestParameterMap(); String strParam
     *                = requestMap.get("param"); if (!NULL_STR.equals(strParam) & strParam != null) { serialNbr =
     *                Integer.parseInt(strParam); // Access on user by id ClassPathXmlApplicationContext context = new
     *                ClassPathXmlApplicationContext("application-context.xml"); UserService userServiceBean =
     *                (UserService) context.getBean("userService"); currentUser = userServiceBean.find(serialNbr); }
     *                else { // currentUser = user; } } return currentUser; }
     */

    public User getCurrentUser() {

        /**
         * The method getCurrentUser could be replace the commented method init() above
         */

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String str = params.get("param");
        if (!str.isEmpty()) {
            user = userServiceBean.find(Integer.parseInt(str));
            return user;
        }
        return null;
    }
}
