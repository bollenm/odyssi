<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>



<html>


<head>
    <spring:url value="/js/bootstrap.css" var="bootstrapCss" />
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">

</head>
<body>

<spring:url value="/js/jquery-3.1.1.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>

<spring:url value="/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"></script>



<script>
    $(document).ready(function(){
        $("p").click(function(){
            $(this).hide();
        });
        var nlpQuery="";

        $("#sendQuery").click(function(){
            nlpQuery= $('#txtQuery').val();
            $.get("/query",
                {
                    nlp: nlpQuery

                },
                function(data, status){
                    var list = $("#results-list");
                    list.empty();
                    for(var key in data){
                        var element = '<li class="list-group-item"><a href=' + data[key]['uri'] + '> '+ data[key]['title'] +'</a>';
                        list.append(element);
                    }
                });
        });


    });


</script>

<div class="jumbotron">
    <h1 class ="display-3"> Speak to your Open Data</h1>

<input class="form-control mr-sm-2" id="txtQuery" type="text" name="" placeholder="Speak...">
<button class="btn btn-secondary" id="sendQuery">Send Query</button>
</div>
<style>
    * {
        font-family: Verdana, Arial, sans-serif;
    }
    a:link {
        color:#000;
        text-decoration: none;
    }
    a:visited {
        color:#000;
    }
    a:hover {
        color:#33F;
    }
    .button {
        background: -webkit-linear-gradient(top,#008dfd 0,#0370ea 100%);
        border: 1px solid #076bd2;
        border-radius: 3px;
        color: #fff;
        display: none;
        font-size: 13px;
        font-weight: bold;
        line-height: 1.3;
        padding: 8px 25px;
        text-align: center;
        text-shadow: 1px 1px 1px #076bd2;
        letter-spacing: normal;
    }
    .center {
        padding: 10px;
        text-align: center;
    }
    .final {
        color: white;
        padding-right: 3px;
    }
    .interim {
        color: gray;
    }
    .info {
        font-size: 14px;
        text-align: center;
        color: #777;
        display: none;
    }
    .right {
        float: right;
    }
    .sidebyside {
        display: inline-block;
        width: 45%;
        min-height: 40px;
        text-align: left;
        vertical-align: top;
    }
    #headline {
        font-size: 40px;
        font-weight: 300;
    }
    #info {
        font-size: 20px;
        text-align: center;
        color: #777;
        visibility: hidden;
    }
    #results {
        font-size: 14px;
        font-weight: bold;
        border: 1px solid #ddd;
        padding: 15px;
        text-align: left;
        min-height: 150px;
    }
    #start_button {
        border: 0;
        background-color:transparent;
        padding: 0;
    }
</style>


<h1 class="center" id="headline">

<div id="info">
    <p id="info_start">Click on the microphone icon and begin speaking.</p>
    <p id="info_speak_now">Speak now.</p>
    <p id="info_no_speech">No speech was detected. You may need to adjust your
        <a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892">
            microphone settings</a>.</p>
    <p id="info_no_microphone" style="display:none">
        No microphone was found. Ensure that a microphone is installed and that
        <a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892">
            microphone settings</a> are configured correctly.</p>
    <p id="info_allow">Click the "Allow" button above to enable your microphone.</p>
    <p id="info_denied">Permission to use microphone was denied.</p>
    <p id="info_blocked">Permission to use microphone is blocked. To change,
        go to chrome://settings/contentExceptions#media-stream</p>
    <p id="info_upgrade">Web Speech API is not supported by this browser.
        Upgrade to <a href="//www.google.com/chrome">Chrome</a>
        version 25 or later.</p>
</div>
<div class="right">
    <button id="start_button" onclick="startButton(event)">
        <img id="start_img" src="mic.gif" alt=""></button>
</div>
<div id="results">
    <span id="final_span" class="final"></span>
    <span id="interim_span" class="interim"></span>
    <p>
</div>
<div id="results">
    <h2>Results:</h2>
    <ul id="results-list" class="list-group">
    </ul>
</div>

<div class="center" style="display:none">
    <div class="sidebyside" style="text-align:right">
        <button id="copy_button" class="button" onclick="copyButton()">
            Copy and Paste</button>
        <div id="copy_info" class="info">
            Press Control-C to copy text.<br>(Command-C on Mac.)
        </div>
    </div>
    <div class="sidebyside">
        <button id="email_button" class="button" onclick="emailButton()">
            Create Email</button>
        <div id="email_info" class="info">
            Text sent to default email application.<br>
            (See chrome://settings/handlers to change.)
        </div>
    </div>
    <p>
    <div id="div_language">
        <select id="select_language" onchange="updateCountry()"></select>
        &nbsp;&nbsp;
        <select id="select_dialect"></select>
    </div>
</div>
<script>
    var langs =
        [['Afrikaans',       ['af-ZA']],
            ['Bahasa Indonesia',['id-ID']],
            ['Bahasa Melayu',   ['ms-MY']],
            ['Català',          ['ca-ES']],
            ['Čeština',         ['cs-CZ']],
            ['Deutsch',         ['de-DE']],
            ['English',         ['en-AU', 'Australia'],
                ['en-CA', 'Canada'],
                ['en-IN', 'India'],
                ['en-NZ', 'New Zealand'],
                ['en-ZA', 'South Africa'],
                ['en-GB', 'United Kingdom'],
                ['en-US', 'United States']],
            ['Español',         ['es-AR', 'Argentina'],
                ['es-BO', 'Bolivia'],
                ['es-CL', 'Chile'],
                ['es-CO', 'Colombia'],
                ['es-CR', 'Costa Rica'],
                ['es-EC', 'Ecuador'],
                ['es-SV', 'El Salvador'],
                ['es-ES', 'España'],
                ['es-US', 'Estados Unidos'],
                ['es-GT', 'Guatemala'],
                ['es-HN', 'Honduras'],
                ['es-MX', 'México'],
                ['es-NI', 'Nicaragua'],
                ['es-PA', 'Panamá'],
                ['es-PY', 'Paraguay'],
                ['es-PE', 'Perú'],
                ['es-PR', 'Puerto Rico'],
                ['es-DO', 'República Dominicana'],
                ['es-UY', 'Uruguay'],
                ['es-VE', 'Venezuela']],
            ['Euskara',         ['eu-ES']],
            ['Français',        ['fr-FR']],
            ['Galego',          ['gl-ES']],
            ['Hrvatski',        ['hr_HR']],
            ['IsiZulu',         ['zu-ZA']],
            ['Íslenska',        ['is-IS']],
            ['Italiano',        ['it-IT', 'Italia'],
                ['it-CH', 'Svizzera']],
            ['Magyar',          ['hu-HU']],
            ['Nederlands',      ['nl-NL']],
            ['Norsk bokmål',    ['nb-NO']],
            ['Polski',          ['pl-PL']],
            ['Português',       ['pt-BR', 'Brasil'],
                ['pt-PT', 'Portugal']],
            ['Română',          ['ro-RO']],
            ['Slovenčina',      ['sk-SK']],
            ['Suomi',           ['fi-FI']],
            ['Svenska',         ['sv-SE']],
            ['Türkçe',          ['tr-TR']],
            ['български',       ['bg-BG']],
            ['Pусский',         ['ru-RU']],
            ['Српски',          ['sr-RS']],
            ['한국어',            ['ko-KR']],
            ['中文',             ['cmn-Hans-CN', '普通话 (中国大陆)'],
                ['cmn-Hans-HK', '普通话 (香港)'],
                ['cmn-Hant-TW', '中文 (台灣)'],
                ['yue-Hant-HK', '粵語 (香港)']],
            ['日本語',           ['ja-JP']],
            ['Lingua latīna',   ['la']]];

    for (var i = 0; i < langs.length; i++) {
        select_language.options[i] = new Option(langs[i][0], i);
    }
    select_language.selectedIndex = 6;
    updateCountry();
    select_dialect.selectedIndex = 6;
    showInfo('info_start');

    function updateCountry() {
        for (var i = select_dialect.options.length - 1; i >= 0; i--) {
            select_dialect.remove(i);
        }
        var list = langs[select_language.selectedIndex];
        for (var i = 1; i < list.length; i++) {
            select_dialect.options.add(new Option(list[i][1], list[i][0]));
        }
        select_dialect.style.visibility = list[1].length == 1 ? 'hidden' : 'visible';
    }

    var create_email = false;
    var final_transcript = '';
    var recognizing = false;
    var ignore_onend;
    var start_timestamp;
    if (!('webkitSpeechRecognition' in window)) {
        upgrade();
    } else {
        start_button.style.display = 'inline-block';
        var recognition = new webkitSpeechRecognition();
        recognition.continuous = true;
        recognition.interimResults = true;

        recognition.onstart = function() {
            recognizing = true;
            showInfo('info_speak_now');
            start_img.src = 'mic-animate.gif';
        };

        recognition.onerror = function(event) {
            if (event.error == 'no-speech') {
                start_img.src = 'mic.gif';
                showInfo('info_no_speech');
                ignore_onend = true;
            }
            if (event.error == 'audio-capture') {
                start_img.src = 'mic.gif';
                showInfo('info_no_microphone');
                ignore_onend = true;
            }
            if (event.error == 'not-allowed') {
                if (event.timeStamp - start_timestamp < 100) {
                    showInfo('info_blocked');
                } else {
                    showInfo('info_denied');
                }
                ignore_onend = true;
            }
        };

        recognition.onend = function() {
            recognizing = false;
            if (ignore_onend) {
                return;
            }
            start_img.src = 'mic.gif';
            if (!final_transcript) {
                showInfo('info_start');
                return;
            }
            showInfo('');
            if (window.getSelection) {
                window.getSelection().removeAllRanges();
                var range = document.createRange();
                range.selectNode(document.getElementById('final_span'));
                window.getSelection().addRange(range);
            }
            if (create_email) {
                create_email = false;
                createEmail();
            }
        };

        recognition.onresult = function(event) {
            var interim_transcript = '';
            for (var i = event.resultIndex; i < event.results.length; ++i) {
                if (event.results[i].isFinal) {
                    final_transcript += event.results[i][0].transcript;
                } else {
                    interim_transcript += event.results[i][0].transcript;
                }
            }
            final_transcript = capitalize(final_transcript);
            final_span.innerHTML = linebreak(final_transcript);
            document.getElementById("txtQuery").value = linebreak(final_transcript);

            interim_span.innerHTML = linebreak(interim_transcript);

            if (final_transcript || interim_transcript) {
                showButtons('inline-block');
            }
        };
    }

    function upgrade() {
        start_button.style.visibility = 'hidden';
        showInfo('info_upgrade');
    }

    var two_line = /\n\n/g;
    var one_line = /\n/g;
    function linebreak(s) {
        return s.replace(two_line, '<p></p>').replace(one_line, '<br>');
    }

    var first_char = /\S/;
    function capitalize(s) {
        return s.replace(first_char, function(m) { return m.toUpperCase(); });
    }

    function createEmail() {
        var n = final_transcript.indexOf('\n');
        if (n < 0 || n >= 80) {
            n = 40 + final_transcript.substring(40).indexOf(' ');
        }
        var subject = encodeURI(final_transcript.substring(0, n));
        var body = encodeURI(final_transcript.substring(n + 1));
        window.location.href = 'mailto:?subject=' + subject + '&body=' + body;
    }

    function copyButton() {
        if (recognizing) {
            recognizing = false;
            recognition.stop();
        }
        copy_button.style.display = 'none';
        copy_info.style.display = 'inline-block';
        showInfo('');
    }

    function emailButton() {
        if (recognizing) {
            create_email = true;
            recognizing = false;
            recognition.stop();
        } else {
            createEmail();
        }
        email_button.style.display = 'none';
        email_info.style.display = 'inline-block';
        showInfo('');
    }

    function startButton(event) {
        if (recognizing) {
            recognition.stop();
            return;
        }
        final_transcript = '';
        recognition.lang = select_dialect.value;
        recognition.start();
        ignore_onend = false;
        final_span.innerHTML = '';
        interim_span.innerHTML = '';
        start_img.src = 'mic-slash.gif';
        showInfo('info_allow');
        showButtons('none');
        start_timestamp = event.timeStamp;
    }

    function showInfo(s) {
        if (s) {
            for (var child = info.firstChild; child; child = child.nextSibling) {
                if (child.style) {
                    child.style.display = child.id == s ? 'inline' : 'none';
                }
            }
            info.style.visibility = 'visible';
        } else {
            info.style.visibility = 'hidden';
        }
    }

    var current_style;
    function showButtons(style) {
        if (style == current_style) {
            return;
        }
        current_style = style;
        copy_button.style.display = style;
        email_button.style.display = style;
        copy_info.style.display = 'none';
        email_info.style.display = 'none';
    }
</script>




</body>
</html>

