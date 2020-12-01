node() {

	timeout(time: 2, unit: 'HOURS') {

		delete_Worspacedir()
		
		stageArray = [
			"Checkout_Project",
			"Build_Project"
		]
	
		try {
			def functionName;
			for (i = 0; i < stageArray.size(); i++) {
				functionName = stageArray.getAt(i)
				println(functionName)
				runFlag = true;
				"$functionName"(runFlag)
			}//for
		}//try
		catch (e) {
			throw e
		}//catch
	}//node
	
}

def delete_Worspacedir() {
	//deleteDir()
	cleanWs()
}

def Checkout_Project(run) {
	stage('Checkout_Project') {
		if(run) {
			checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/kaushiksen1984/demoinventory.git']]])
		}
	}
}

def Build_Project(run) {
	stage('Build_Project') {
		if(run) {
			echo 'test'
			sh "mvn clean deploy -PrunPIT -PrunJacoco -PrunSonar -Djacoco.haltOnFailure=false -Dsonar.buildbreaker.skip=true"
		}
	}
}