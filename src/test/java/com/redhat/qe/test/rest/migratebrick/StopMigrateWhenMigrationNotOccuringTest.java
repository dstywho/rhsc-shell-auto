package com.redhat.qe.test.rest.migratebrick;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.google.common.base.Function;
import com.redhat.qe.annoations.Tcms;
import com.redhat.qe.factories.VolumeFactory;
import com.redhat.qe.helpers.Asserts;
import com.redhat.qe.helpers.repository.StepsRepositoryHelper;
import com.redhat.qe.model.Brick;
import com.redhat.qe.model.Job;
import com.redhat.qe.model.Step;
import com.redhat.qe.model.Volume;
import com.redhat.qe.model.jaxb.MigrateBrickAction;
import com.redhat.qe.repository.JobRepository;
import com.redhat.qe.repository.rest.BrickRepository;
import com.redhat.qe.repository.rest.ResponseWrapper;
import com.redhat.qe.repository.rest.StepRepository;
import com.redhat.qe.ssh.ExecSshSession.Response;
import com.redhat.qe.test.rest.PopulatedVolumeTestBase;

public class StopMigrateWhenMigrationNotOccuringTest extends MigrateTestBase{

	@Override
	protected Volume getVolumeToBeCreated() {
		return VolumeFactory.distributed("StopMigNotMigrating",4, getHost1(), getHost2());
	}
//	
//	@Override
//	public void cleanupRhsc(){
//		//TODO don't clean up
//	}
//	
//	@Override
//	public void destroyVolume(){
//		//TodO dont' do that
//	}
//	
	
	protected void populateVolume() {
	}
	
	@Test
	@Tcms({"321103"})
	public void test(){
		BrickRepository brickRepo = new BrickRepository(getSession(), volume.getCluster(), volume);
		ArrayList<Brick> bricks = brickRepo.list();

//		MigrateBrickAction migrateAction = brickRepo.migrate(bricks.get(0), bricks.get(1));
//		Job migrateJob = getJob(migrateAction);
//		Step executingStep = new StepsRepositoryHelper().getExecutingStep(getStepRepo(migrateJob));


		ResponseWrapper response = brickRepo._stopMigrate(bricks.get(0));
		response.expect("(?i)not started");
		
	}



}
