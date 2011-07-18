<div id="student">
    <form id="updateStudent"
          name="updateStudent"
          action="/students/$student.studentId$"
          enctype="multipart/form-data"
          method="post">
            <script type = "text/javascript" src="/scripts/studentUpdate.js"></script>

        <div id="studentInfo">
            <div id="basicInfo">
                <div class="updateEntry">
                    <div class="label">Student ID</div>
                    <input type="text" name="studentId" id="studentId" value="$student.studentId$" readonly
                           class="updateValue"></input>
                </div>

                <div class="updateEntry">
                    <div class="label">Name<span class="mandatory">*</span></div>
                    <input type="text" id="name" name="name" value="$student.name$" +class="updateValue"></input>
                </div>

                <div class="updateEntry">
                    <div class="label">Date of Birth<br/>(dd-mm-yyyy)<span class="mandatory">*</span></div>
                    <input type="text" id="dateOfBirth" name="dateOfBirth" value="$student.dateOfBirthForDisplay$" class="updateValue"></input>
                </div>

                <div class="updateEntry">
                    <div class="label">Gender<span class="mandatory">*</span></div>
                    <select name="gender" id="gender" class="updateValue">
                        $formhelper.gendersDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Class</div>
                    <select name="studentClass" class="updateValue">
                        $formhelper.classesDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Religion</div>
                    <select name="religion" class="updateValue">
                        $formhelper.religionsDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Caste</div>
                    <select name="caste" class="updateValue">
                        $formhelper.castesDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Subcaste</div>
                    <select name="subCaste" class="updateValue">
                        $formhelper.subCastesDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Community Location</div>
                    <select name="communityLocation" class="updateValue">
                        $formhelper.communityLocationsDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                <div class="label">Family Status</div>
                    <select name="familyStatus" class="updateValue" id="familystatus">
                        $formhelper.familyStatusesDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>


                <div class="updateEntry">
                    <div class="label-header">Background</div>
                    <textarea autocomplete="off" cols="45" rows="3" class="noHorizontalResize" name="background" id="edit-background" wrap = "soft"
                            onKeyDown = "limitText(this.form.background,500);">$student.profile.background$</textarea>
                </div>

                <div class="updateEntry">
                    <div class="label-header">Academic Performance</div>
                    <textarea autocomplete="off" cols="45" rows="3" class="noHorizontalResize" name="performance" id="edit-performance" wrap = "soft"
                            onKeyDown = "limitText(this.form.performance,500);">$student.performance$</textarea>
                </div>


                <div class="updateEntry">
                    <div class="label-header">Disciplinary</div>
                    <textarea autocomplete="off" cols="45" rows="3" class="noHorizontalResize" name="disciplinary" id="edit-disciplinary" wrap= "soft"
                            onKeyDown = "limitText(this.form.disciplinary,500);">$student.disciplinary$</textarea>
                </div>

            </div>

                <div class="statusLabel">Student Status<span class="mandatory">*</span></div>
                <div class="statusLabel">
                <select name="status" id="status" class="updateStatusValue">
                    $formhelper.statusesDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                </select>
                </div>


            <div class="studentImage">

                <img src="/students/$student.imageLink$/image" id="$student.imageLink$"/>
                <input size="15" type="file" name="imageToUpload" id="imageToUpload"/>
                <span class="errorMessage">$imageToUploadError$</span>
            </div>

            <div class ="updateSponsorDetails">
            <div class ="sponsoredMessage">If adding a sponsor, enter all 3 following details</div>
            </div>

            <div class="updateSponsorDetails">
                <div class="sponsoredLabel"><label id="Sponsor" for="sponsored">Sponsor</label></div>
                <div class="sponsorValue"><input type="text" name="sponsored" id="sponsored" value="$student.sponsor$" ></input></div>
            </div>
            <div class="updateSponsorDetails">
            <div class="sponsoredLabel"><label id="Sponsor Email" for="sponsored">Sponsor Email</label></div>
                <div class="sponsorValue"><input type="text" name="sponsorEmail" id="SponsorEmail" value="$student.sponsorEmail$" ></input></div>
            </div>

            <div class="updateSponsorDetails">
                 <div class="sponsoredLabel"><label id="Sponsor Start Date" for="sponsored">Start Date</label><br/> (dd-mm-yyyy)</div>
                 <div class="sponsorValue"><input type="text" name="sponsorStartDate" id="SponsorStartDate" value="$student.sponsorStartDateForDisplay$" ></input></div>
            </div>
            

            <div class="talentsList">
                <div class="talentsLabel">Talents</div>
                <div class="talentsCheckBoxes">
                    $formhelper.talentsCheckBoxList:{
                    <div>
                        <input type="checkbox" name="talents" value="$it.value$" $if (it.checked)$ checked $endif$>
                            $it.value$
                        </input>
                    </div>
                    }$
                </div>
            </div>

            <div id="parentDetails">
                <h2>Parent Details</h2>
                <div id="fatherDetails">
                    <div class="labelHead">Father</div>
                    <div class="updateEntry">
                        <div class="label">Name</div>
                        <input type="text" name="father.name" value="$student.father.name$" class="updateValue"></input>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Education</div>
                        <input type="text" name="father.education" value="$student.father.education$" class="updateValue"></input>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Contact</div>
                        <input type="text" name="father.contact" value="$student.father.contact$" class="updateValue"></input>
                    </div>
                    <div class="updateEntrySelect">
                        <div class="label">Occupation</div>
                        <select name="father.occupation">
                            <option value=""></option>
                             $formhelper.fatherOccupationsDropDownList:{
                                <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                             }$
                        </select>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Salary</div>
                        <input type="text" name="father.salary" value="$student.father.salary$" class="updateValue"></input>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Marital Status</div>
                        <select name="father.maritalStatus">
                             $formhelper.fatherMaritalStatusesDropDownList:{
                                <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                             }$
                        </select>
                     </div>
                </div> <!-- end father -->
                <div id="motherDetails">
                    <div class="labelHead">Mother</div>
                    <div class="updateEntry">
                        <div class="label">Name</div>
                        <input type="text" name="mother.name" value="$student.mother.name$" class="updateValue"></input>
                    </div>
                     <div class="updateEntry">
                        <div class="label">Education</div>
                        <input type="text" name="mother.education" value="$student.mother.education$" class="updateValue"></input>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Contact</div>
                        <input type="text" name="mother.contact" value="$student.mother.contact$" class="updateValue"></input>
                    </div>
                    <div class="updateEntrySelect">
                        <div class="label">Occupation</div>
                        <select name="mother.occupation">
                            <option value=""></option>
                             $formhelper.motherOccupationsDropDownList:{
                                <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                             }$
                        </select>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Salary</div>
                        <input type="text" name="mother.salary" value="$student.mother.salary$" class="updateValue"></input>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Marital Status</div>
                        <select name="mother.maritalStatus">
                             $formhelper.motherMaritalStatusesDropDownList:{
                                <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                             }$
                        </select>
                    </div>
                 </div> <!-- end mother -->
                 <div id="guardianDetails">
                    <div class="labelHead">Guardian</div>
                    <div class="updateEntry">
                        <div class="label">Name</div>
                        <input type="text" name="guardian.name" value="$student.guardian.name$" class="updateValue"></input>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Education</div>
                        <input type="text" name="guardian.education" value="$student.guardian.education$" class="updateValue"></input>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Contact</div>
                        <input type="text" name="guardian.contact" value="$student.guardian.contact$" class="updateValue"></input>
                    </div>
                    <div class="updateEntrySelect">
                        <div class="label">Occupation</div>
                        <select name="guardian.occupation">
                            <option value=""></option>
                             $formhelper.guardianOccupationsDropDownList:{
                                <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                             }$
                        </select>
                    </div>
                    <div class="updateEntry">
                        <div class="label">Salary</div>
                        <input type="text" name="guardian.salary" value="$student.guardian.salary$" class="updateValue"></input>
                    </div>

                </div> <!-- end guardian -->
                </div>
            </div>
        </div>

        <div id="update">
            <input type="button" id="save" value="Save"/>
            <input type="button" id="cancel" value="Cancel" />
        </div>

        <div id="errorMessages">
        </div>

    </form>
</div>