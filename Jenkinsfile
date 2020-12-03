node() {

	timeout(time: 2, unit: 'HOURS') {

		delete_Worspacedir()
		
		stageArray = [
			"Checkout_Project",
			"Build_Project",
			"Deploy_Application",
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
			def subject = "Pipeline successful Job ${env.JOB_NAME}[${env.BUILD_NUMBER}]"
			def details = "Build Successful. Check console output at ${env.BUILD_URL} \n\nNode name: ${env.NODE_NAME}"
			emailext body: details, subject: subject, to: 'senkaushikdevops@gmail.com'
		}//try
		catch (e) {
			def subject = "Pipeline failed Job ${env.JOB_NAME}[${env.BUILD_NUMBER}]"
			def details = "Build Failed. Check console output at ${env.BUILD_URL} \n\nNode name: ${env.NODE_NAME}"
			emailext body: details, subject: subject, to: 'senkaushikdevops@gmail.com'
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

def Deploy_Application(run) {
	stage('Deploy_Application') {
		if(run) {
			deploy adapters: [tomcat9(credentialsId: 'TomcatDepoymentId', path: '', url: 'http://localhost:8085')], contextPath: '/v1', war: '**/*.war'
		}
	}
}

def Run_BDD(run) {
	stage('Run_BDD') {
		if(run) {
			sh "mvn test -PrunBDD -Dtest=DemoinventoryBDDRunner -Dkarate.baseURL=http://localhost:8085/v1/products"
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