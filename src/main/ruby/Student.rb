require 'rubygems'

class Student
  
  def initialize(data)
    @sl = Integer(data[0])
    @name = data[1]
    @gender = data[2]
    @father = data[3]
    @mother = data[4]
    @dob = data[5]
    @contact = data[6]  
  end
  
  def to_s
    "#{@sl},#{@name},#{@gender},#{@father},#{@mother},#{@dob},#{@contact}"
  end
  
end
