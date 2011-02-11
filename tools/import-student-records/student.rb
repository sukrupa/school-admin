require 'rubygems'

class Student

  attr_reader :caste, :sub_caste, :community_location, :student_id, :father, :mother, :name, :date_of_birth, :gender, :religion, :student_class
  
  def initialize args
    args.each do |k,v|
      instance_variable_set("@#{k}", parse(v)) unless (v.nil? or v.empty?)
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
  
  def parse(value)
    valid_date?(value) ? format_date(value) : value
  end
  
  def valid_date?(date)
    begin
      parse_date date
      return true;
    rescue
      return false;
    end
  end
  
  def parse_date(date)
    Date.strptime(date, '%d/%m/%Y')
  end
  
  def format_date(date)
    return parse_date(date).strftime("%Y-%m-%d")
  end
  
end
