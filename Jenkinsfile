node() {

	timeout(time: 2, unit: 'HOURS') {

		delete_Worspacedir()
		setPipeLineParameters()
		
		echo appversion
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
	deleteDir()
}

def notifySuccess() {
	branchName = 'demoinventory'
	groupId = 'com.products'
	def details = "Pipeline successful : Job ${groupId}_Pipeline_${branchName} [${env.BUILD_NUMBER}]: Pipeline can be accessed at the following link: ${env.BUILD_URL} \n\nAppVersion: ${appversion} \nNode Name: ${env.NODE_NAME}"
	emailext body: details, subject: "Pipeline successful : Job '${groupId}_Pipeline_${branchName} [${env.BUILD_NUMBER}']", to: "kaushik.extc@gmail.com"\
}

def notifyFailed() {
	branchName = 'demoinventory'
	groupId = 'com.products'
	def details = "Pipeline FAILED : Job ${groupId}_Pipeline_${branchName} [${env.BUILD_NUMBER}]: Pipeline can be accessed at the following link: ${env.BUILD_URL} \n\nAppVersion: ${appversion} \nNode Name: ${env.NODE_NAME}"
	emailext body: details, subject: "Pipeline FAILED : Job '${groupId}_Pipeline_${branchName} [${env.BUILD_NUMBER}']", to: "kaushik.extc@gmail.com"\
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