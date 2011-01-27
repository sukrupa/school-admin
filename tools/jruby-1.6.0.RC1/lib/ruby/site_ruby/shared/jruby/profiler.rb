
require 'java'

module JRuby
  module Profiler
    import org.jruby.runtime.profile.GraphProfilePrinter
    import org.jruby.runtime.profile.FlatProfilePrinter
    
    def self.start
      current_thread_context.start_profiling
    end
    
    def self.stop
      current_thread_context.stop_profiling
    end
    
    def self.results
      current_thread_context.profile_data
    end
    
    def self.profile
      yield
    end
    
    private
    
    def self.runtime
      org.jruby.Ruby.getGlobalRuntime
    end
    
    def self.current_thread_context
      runtime.get_thread_service.get_current_context
    end
  end
end