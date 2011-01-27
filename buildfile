# Uncomment this to download extra dependencies to the local repository. Never commit it uncommented.
# repositories.remote << 'http://www.ibiblio.org/maven2'
repositories.local = './tools/lib'

define 'sukrupa' do

	compile.with Dir[_("lib/main/*.jar")]
	compile.into "web/WEB-INF/classes"

	test.with Dir[_("lib/test/*.jar")]
	test.compile.from "src/test/unit"
	test.compile.into "target/test/unit"
    test.teardown do
        # FIXME hack to get reports into target/reports
        # because we can't find the buildr config option
        rm_rf "target/reports"
        mv "reports/junit", "target/reports", :force => true
        rmdir "reports"
    end

	package :war, :file=>_('target/sukrupa.war')

    #TODO: fork off webserver
	run.using :main => "org.sukrupa.web.WebServer"
end
