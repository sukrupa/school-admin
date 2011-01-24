repositories.remote << 'http://www.ibiblio.org/maven2/'

define 'sukrupa' do
	compile.with Dir[_("lib/**/*.jar")]
	test.with Dir[_("lib/test/*.jar")]
end
