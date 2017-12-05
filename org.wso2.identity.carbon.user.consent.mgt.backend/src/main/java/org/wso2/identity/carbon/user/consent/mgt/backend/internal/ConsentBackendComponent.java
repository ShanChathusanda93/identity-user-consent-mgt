package org.wso2.identity.carbon.user.consent.mgt.backend.internal;

import org.wso2.identity.carbon.user.consent.mgt.backend.service.ConsentBackend;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.wso2.identity.carbon.user.consent.mgt.backend.service.ConsentBackendImpl;

@Component(name = "org.wso2.identity.carbon.user.consent.mgt.backend.internal.ConsentBackendComponent", immediate = true)
public class ConsentBackendComponent {
    @Activate
    protected void activate(BundleContext bundleContext){
        bundleContext.registerService(ConsentBackend.class, new ConsentBackendImpl(),null);
    }
}
