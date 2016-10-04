
package org.reflection.model.com;

import java.util.Date;
import org.reflection.model.security.AuthUser;

public interface IAuditable {

    public AuthUser getEntryBy();

    public void setEntryBy(AuthUser entryBy);

    public Date getEntryDate();

    public void setEntryDate(Date entryDate);

    public AuthUser getEditBy();

    public void setEditBy(AuthUser editBy);

    public Date getEditDate();

    public void setEditDate(Date editDate);

}
