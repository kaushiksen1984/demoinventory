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
			notifySuccess()
		}//try
		catch (e) {
			notifyFailed()
			throw e
		}//catch
	}//node
	
}

def delete_Worspacedir() {
	//deleteDir()
	cleanWs()
}

def notifySuccess() {
	branchName = 'demoinventory'
	groupId = 'com.products'
	def details = "Pipeline successful"
	emailext body: details, subject: "Pipeline successful", to: "kaushik.extc@gmail.com"\
}

def notifyFailed() {
	branchName = 'demoinventory'
	groupId = 'com.products'
	def details = "Pipeline FAILED"
	emailext body: details, subject: "Pipeline FAILED", to: "kaushik.extc@gmail.com"\
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
			sh "mvn clean deploy -PrunPIT -PJacoco -PrunSonar -Djacoco.haltOnFailure=false -Dsonar.buildbreaker.skip=true"
		}
	}
}