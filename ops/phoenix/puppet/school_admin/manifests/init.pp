class school_admin() {

package{'openjdk-6-jdk':
    ensure => present
   }

package{'unzip':
	ensure => present
}

exec { 'run school_admin':
    command => 'sudo unzip -u puppet/school-admin.zip -d ~/school_admin; cd school_admin; sudo sh start-server.sh',
    path => ['/usr/bin'],
    require => [Package['unzip'],Package['openjdk-6-jdk']]
    }
}

