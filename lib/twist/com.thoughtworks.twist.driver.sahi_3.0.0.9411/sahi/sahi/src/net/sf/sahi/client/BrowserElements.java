package net.sf.sahi.client;

/**
 * Sahi - Web Automation and Test Tool
 * 
 * Copyright  2006  V Narayan Raman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * BrowserElements represents the different Accessor APIs that Sahi exposes.<br/>
 * Each of the APIs returns an ElementStub which is a representation of a particular 
 * HTML DOM element on the browser.<br/>
 * 
 * Have a look at <a href="http://sahi.co.in/w/browser-accessor-apis">Sahi Browser Accessor APIS</a>
 * for more information on each accessor.
 * 
 * Note that regular expressions based accessors are different from Sahi's native accessors in that they are quoted as Strings:<br/><br/>
 * Example:
 * 		<code>_link(/visible .*ext/)</code> in Sahi Script is equivalent to <br>
 * 		<code>browser.link("/visible .*ext/")
 *  
 * 
 */
public abstract class BrowserElements {
	protected Browser browser;
	/**
	 * Defines a generic accessor. <br/>
	 * A javascript eval will be performed on the parameter passed.<br/>
	 * Use this to execute custom javascript accessors when no other Sahi accessors can be used.<br/>
	 * <br/>
	 * Example:<br/>
	 * 		<code>browser.accessor("document.form1.textelement1").click()</code><br/>
	 * 
	 * will eval "document.form1.textelement1" on the browser then invoke a click on it.
	 * 
	 *  
	 * @param args
	 * @return a stub representing the accessor
	 */
	public ElementStub accessor(Object... args) {return new ElementStub("accessor", browser, args);}
	public ElementStub button(Object... args) {return new ElementStub("button", browser, args);}
	public ElementStub checkbox(Object... args) {return new ElementStub("checkbox", browser, args);}
	public ElementStub image(Object... args) {return new ElementStub("image", browser, args);}
	public ElementStub imageSubmitButton(Object... args) {return new ElementStub("imageSubmitButton", browser, args);}
	public ElementStub link(Object... args) {return new ElementStub("link", browser, args);}
	public ElementStub password(Object... args) {return new ElementStub("password", browser, args);}
	public ElementStub radio(Object... args) {return new ElementStub("radio", browser, args);}
	public ElementStub select(Object... args) {return new ElementStub("select", browser, args);}
	public ElementStub submit(Object... args) {return new ElementStub("submit", browser, args);}
	public ElementStub textarea(Object... args) {return new ElementStub("textarea", browser, args);}
	public ElementStub textbox(Object... args) {return new ElementStub("textbox", browser, args);}
	public ElementStub event(Object... args) {return new ElementStub("event", browser, args);}
	public ElementStub cell(Object... args) {return new ElementStub("cell", browser, args);}
	public ElementStub table(Object... args) {return new ElementStub("table", browser, args);}
	public ElementStub byId(Object... args) {return new ElementStub("byId", browser, args);}
	public ElementStub byClassName(Object... args) {return new ElementStub("byClassName", browser, args);}
	public ElementStub byXPath(Object... args) {return new ElementStub("byXPath", browser, args);}
	public ElementStub row(Object... args) {return new ElementStub("row", browser, args);}
	public ElementStub div(Object... args) {return new ElementStub("div", browser, args);}
	public ElementStub span(Object... args) {return new ElementStub("span", browser, args);}
	public ElementStub spandiv(Object... args) {return new ElementStub("spandiv", browser, args);}
	public ElementStub option(Object... args) {return new ElementStub("option", browser, args);}
	public ElementStub reset(Object... args) {return new ElementStub("reset", browser, args);}
	public ElementStub file(Object... args) {return new ElementStub("file", browser, args);}
//	public ElementStub get(Object... args) {return new ElementStub("get", browser, args);}
//	public ElementStub style(Object... args) {return new ElementStub("style", browser, args);}
	public ElementStub byText(Object... args) {return new ElementStub("byText", browser, args);}
	public ElementStub cookie(Object... args) {return new ElementStub("cookie", browser, args);}
	public ElementStub position(Object... args) {return new ElementStub("position", browser, args);}
	public ElementStub label(Object... args) {return new ElementStub("label", browser, args);}
//	public ElementStub rteHTML(Object... args) {return new ElementStub("rteHTML", browser, args);}
//	public ElementStub rteText(Object... args) {return new ElementStub("rteText", browser, args);}
	public ElementStub prompt(Object... args) {return new ElementStub("prompt", browser, args);}
	public ElementStub list(Object... args) {return new ElementStub("list", browser, args);}
	public ElementStub listItem(Object... args) {return new ElementStub("listItem", browser, args);}
	public ElementStub parentNode(Object... args) {return new ElementStub("parentNode", browser, args);}
	public ElementStub parentCell(Object... args) {return new ElementStub("parentCell", browser, args);}
	public ElementStub parentRow(Object... args) {return new ElementStub("parentRow", browser, args);}
	public ElementStub parentTable(Object... args) {return new ElementStub("parentTable", browser, args);}
	public ElementStub in(Object... args) {return new ElementStub("in", browser, args);}
	public ElementStub near(Object... args) {return new ElementStub("near", browser, args);}
	public ElementStub rte(Object... args) {return new ElementStub("rte", browser, args);}
	public ElementStub iframe(Object... args) {return new ElementStub("iframe", browser, args);}
	public ElementStub tableHeader(Object... args) {return new ElementStub("tableHeader", browser, args);}
	public ElementStub heading1(Object... args) {return new ElementStub("heading1", browser, args);}
	public ElementStub heading2(Object... args) {return new ElementStub("heading2", browser, args);}
	public ElementStub heading3(Object... args) {return new ElementStub("heading3", browser, args);}
	public ElementStub heading4(Object... args) {return new ElementStub("heading4", browser, args);}
	public ElementStub heading5(Object... args) {return new ElementStub("heading5", browser, args);}
	public ElementStub heading6(Object... args) {return new ElementStub("heading6", browser, args);}
	public ElementStub hidden(Object... args) {return new ElementStub("hidden", browser, args);}
	public ElementStub area(Object... args) {return new ElementStub("area", browser, args);}
	public ElementStub map(Object... args) {return new ElementStub("map", browser, args);}
	public ElementStub italic(Object... args) {return new ElementStub("italic", browser, args);}
	public ElementStub bold(Object... args) {return new ElementStub("bold", browser, args);}
	public ElementStub emphasis(Object... args) {return new ElementStub("emphasis", browser, args);}
	public ElementStub strong(Object... args) {return new ElementStub("strong", browser, args);}
	public ElementStub preformatted(Object... args) {return new ElementStub("preformatted", browser, args);}
	public ElementStub code(Object... args) {return new ElementStub("code", browser, args);}
	public ElementStub blockquote(Object... args) {return new ElementStub("blockquote", browser, args);}
	public ElementStub xy(Object... args) {return new ElementStub("xy", browser, args);}
}
