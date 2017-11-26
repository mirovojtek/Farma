
package farma;

import storage.MemoryZvieraDao;

public enum DaoFactory {
    
    INSTANCE;
    
    public ZvieraDao getZvieraDao(){
        return new MemoryZvieraDao();
    }
    
}
