package edu.osu.library.dspace.statistics;

import org.apache.log4j.Logger;
import org.dspace.eperson.EPerson;
import org.dspace.services.model.Event;
import org.dspace.usage.AbstractUsageEventListener;
import org.dspace.usage.UsageEvent;

public class ElasticSearchLoggerEventListener extends AbstractUsageEventListener {

    private static Logger log = Logger.getLogger(ElasticSearchLoggerEventListener.class);


    public void receiveEvent(Event event) {

        if(event instanceof UsageEvent)
        {
            log.info("HEY EVERYBODY!");
            try{

                UsageEvent ue = (UsageEvent) event;

                EPerson currentUser = ue.getContext() == null ? null : ue.getContext().getCurrentUser();

                ElasticSearchLogger.post(ue.getObject(), ue.getRequest(), currentUser);
                log.info("Successfully logged " + ue.getObject().toString());
            }
            catch(Exception e)
            {
                log.error("General Exception: " + e.getMessage());
            }
        }
    }
}
