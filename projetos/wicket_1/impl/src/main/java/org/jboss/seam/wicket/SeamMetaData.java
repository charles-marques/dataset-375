package org.jboss.seam.wicket;

import org.apache.wicket.MetaDataKey;

/**
 * Public storage for the metadata key used by the Seam integration to store
 * conversation ids in wicket page metadata.
 *
 * @author cpopetz
 */
public class SeamMetaData {

    /**
     * This is the key we will use to to store the conversation metadata in the
     * wicket page.
     */
    public static final MetaDataKey<String> CID = new MetaDataKey<String>() {
        private static final long serialVersionUID = -8788010688731927318L;
    };
}
