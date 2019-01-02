package team.rpsg.html.dom

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Container
import com.badlogic.gdx.scenes.scene2d.ui.Value
import groovy.transform.CompileStatic
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import team.rpsg.html.util.ColorParser
import team.rpsg.html.util.SizeParser

@CompileStatic
class Text extends Dom {

	int fontSize = 30
	def text = ""
	Color textColor = Color.WHITE

	Text(Node node) {
		super(node)
	}

	void parse() {
		super.parse()

		text = (node as TextNode).text()
		textColor = style("color", "white", ColorParser.&parse, true, true) as Color
		fontSize = style("font-size", "16px", SizeParser.&parsePX, true, true) as int
	}

	void build() {
		super.build()
		def label = res.text.getLabel(text, fontSize)
		label.color = textColor

		def wrapProperty = style("-gdx-wrap", "false", {p -> p?.toString()})
		def markup = style("-gdx-markup", "false", {p -> p?.toString().toBoolean()})

		if(markup)
			label.style.font.data.markupEnabled = markup

		if(wrapProperty && wrapProperty == "true"){
			label.wrap = true

			def conatiner = new Container(label)
			conatiner.width(getParentDom().width)
			current.addActor conatiner
		}else {
			current.addActor label
		}





	}

	void dispose() {

	}
}
