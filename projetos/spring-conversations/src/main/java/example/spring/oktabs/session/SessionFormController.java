package example.spring.oktabs.session;

import example.DomainObjectRepository;
import example.spring.oktabs.OkTabsFormController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SessionFormController extends OkTabsFormController {

    @Autowired
    public SessionFormController(DomainObjectRepository repository) {
        super(repository, "OK TABS (with session)");
    }
}
