require 'rubygems'

class Student

  attr_reader :community_location, :student_id, :father, :mother, :name, :date_of_birth, :gender, :religion, :student_class
  
  def initialize args
    args.each do |k,v|
      if k == :date_of_birth
        instance_variable_set("@#{k}", format_date(v)) unless (v.nil? or v.empty?)
        else
        instance_variable_set("@#{k}", v) unless (v.nil? or v.empty?)
      end
    end
  end
  
  def attributes
    attributes = {}
    instance_variables.each do |variable|
      variable_name = variable.sub('@','')
      variable_value = send:"#{variable_name}"
      attributes[variable_name] = variable_value
    end
    attributes
  end
  
  def attribute_names
    attributes.keys
  end
  
  def attribute_values
    attributes.values
  end
  
  def parse_date(date)
    Date.strptime(date, '%d-%m-%Y')
  end
  
  def format_date(date)
    return parse_date(date).strftime("%Y-%m-%d")
  end
  
end
