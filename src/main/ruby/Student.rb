require 'rubygems'

class Student

  attr_reader :name
  
  def initialize args
    args.each do |k,v|
      instance_variable_set("@#{k}", v) unless v.nil?
    end
  end
  
  def fromCSV(data)
    
  end
  
  def to_s
    "#{@sl},#{@name},#{@gender},#{@father},#{@mother},#{@dob},#{@contact}"
  end
  
end
