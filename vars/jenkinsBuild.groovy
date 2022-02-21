def call(String registryCred = 'a', String registryin = 'a', String docTag = 'a', String contName = 'a') {

pipeline {
environment { 
		registryCredential = "${registryCred}"
		registry = "$registryin" 	
		dockerTag = "${docTag}_$BUILD_NUMBER"
		containerName = "${contName}"
	}
		
	agent { label 'dockerlabel' }
	
	triggers {
		pollSCM '* * * * *'
	}

	stages {
		stage("POLL SCM"){
			steps {
				  git branch: 'main', url: 'https://github.com/JevitaD/nodejs-k8s.git'
			}
		}
	}
}								  

}
