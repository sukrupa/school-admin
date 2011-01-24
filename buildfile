repositories.remote << 'http://www.ibiblio.org/maven2/'

define 'sukrupa' do
	compile.with Dir[_("lib/*.jar")]
	compile.with Dir[_("lib/jetty/*.jar")]
	compile.with Dir[_("lib/jsp/*.jar")]
	compile.with Dir[_("lib/spring/*.jar")]
	compile.into "web/WEB-INF/classes"

	test.with Dir[_("lib/test/*.jar")]
	test.compile.into "target/test"

	package :war, :file=>_('target/sukrupa.war')
end
