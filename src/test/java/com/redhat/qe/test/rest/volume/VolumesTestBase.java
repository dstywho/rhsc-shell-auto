package com.redhat.qe.test.rest.volume;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.redhat.qe.config.RhscConfiguration;
import com.redhat.qe.helpers.repository.ClusterHelper;
import com.redhat.qe.model.Cluster;
import com.redhat.qe.model.Host;
import com.redhat.qe.model.Volume;
import com.redhat.qe.repository.rest.VolumeRepository;
import com.redhat.qe.test.rest.HostClusterTestBase;

public abstract class VolumesTestBase extends HostClusterTestBase{
	

	private ArrayList<Volume> volumes;


	
	protected ArrayList<Volume> getVolumes(){
		return volumes;
	}
	
	@Before
	public void createVolume(){
		ArrayList<Volume> result = new ArrayList<Volume>();
		for(final Volume volume: getVolumesToBeCreated()){
			result.add(getVolumeRepository(volume.getCluster()).createOrShow(volume));
			this.volumes = result;
		}
			
	}

	/**
	 * @param volume
	 * @return
	 */

	
	protected abstract List<Volume> getVolumesToBeCreated();


}
