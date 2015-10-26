package sddc.genericapi;

import org.libvirt.Connect;
import org.libvirt.LibvirtException;
import org.libvirt.StoragePool;

import sddc.dataaccess.IGenericAPIFacade;

public class GenericAPIImpl implements IGenericAPIFacade {
	
	private Connect conn;

	@Override
	public void connect(String uri, boolean readOnly) throws LibvirtException {
		try {
			conn = new Connect(uri, readOnly);
		} catch (LibvirtException libvirtException) {
			//Logging
			throw libvirtException;
		}
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String createStorage(String config) throws LibvirtException {
		try {
			StoragePool storagePool = conn.storagePoolCreateXML(config, 0);
			return storagePool.getUUIDString();
		} catch (LibvirtException libvirtException) {
			//Logging
			throw libvirtException;
		}
	}

	@Override
	public void deleteStorage(String uuid) throws LibvirtException {
		try {
			StoragePool storagePool = conn.storagePoolLookupByUUIDString(uuid);
			storagePool.destroy();
			storagePool.undefine();
		} catch (LibvirtException libvirtException) {
			//Logging
			throw libvirtException;
		}
	}

	@Override
	public String getStorage(String uuid) throws LibvirtException {
		try {
			StoragePool storagePool = conn.storagePoolLookupByUUIDString(uuid);
			return storagePool.toString();
		} catch(LibvirtException libvirtException) {
			//Logging
			throw libvirtException;
		}
	}

}
