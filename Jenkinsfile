node() {

	timeout(time: 2, unit: 'HOURS') {

		delete_Worspacedir()
		
		stageArray = [
			"Checkout_Project",
			"Build_Project",
			"Run_BDD"
		]
	
		try {
			def functionName;
			for (i = 0; i < stageArray.size(); i++) {
				functionName = stageArray.getAt(i)
				println(functionName)
				runFlag = true;
				"$functionName"(runFlag)
			}//for
			def details = "Build Successful. Check console output at ${env.BUILD_URL}"
			emailext body: details, subject: 'Build Successful', to: 'senkaushikdevops@gmail.com'
		}//try
		catch (e) {
			def details = "Build Failed. Check console output at ${env.NODE_NAME}"
			emailext body: details, subject: 'Build Failed', to: 'senkaushikdevops@gmail.com'
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
			sh "mvn clean deploy -PrunPIT -PrunJacoco -PrunSonar -Djacoco.haltOnFailure=false -Dsonar.buildbreaker.skip=true"
			saveReportHelper('target/site/jacoco', 'JacocoReport')
			saveReportHelper('target/pit-reports', 'PITReport')
			
			junit testResults: '**/target/surefire-reports/TEST-*.xml'
			publishHTML(target: [allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'target', reportFiles: 'site/jacoco/index.html', reportName: 'Jacoco Report'])
			publishHTML(target: [allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'target', reportFiles: 'pit-reports/index.html', reportName: 'PIT Report'])
		}
	}
}

def Run_BDD(run) {
	stage('Run_BDD') {
		if(run) {
			sh "mvn test -PrunBDD -Dtest=DemoinventoryBDDRunner -Dkarate.baseURL=http://localhost:9080/products"
			saveReportHelper('target/cucumber-html-reports', 'CucumberReport')
			publishHTML(target: [allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'target', reportFiles: 'cucumber-html-reports/overview-features.html', reportName: 'Cucumber Report'])
		}
	}
}

def saveReportHelper(sourceFolderLoc,subFolderName) {
	def destinationFolder = "reports/${subFolderName}"
	sh "mkdir -p ${destinationFolder}"
	sh "cp -R ${sourceFolderLoc} ${destinationFolder}"
}