var BASE_URL = "http://123.206.84.137/ApplicationPraticeCode/php/receive.php?PostType=";

$.post = function (a, b, c) {
    $.ajax({
        url: a,
        type: 'post',
        data: typeof(b) == 'object' ? b : {},
        dataType: 'json',
        cache: false,
        traditional: true,
        success: typeof(b) == 'function' ? b :
            typeof(c) == 'function' ? c : function () {
            }
    });
};

$_GET = (function () {
    var url = window.document.location.href.toString();
    var u = url.split("?");
    if (typeof(u[1]) == "string") {
        u = u[1].split("&");
        var get = {};
        for (var i in u) {
            var j = u[i].split("=");
            get[j[0]] = j[1];
        }
        return get;
    } else {
        return {};
    }
})();

$(document).ready(function () {
    $("#searchBar").load("searchBar.html");
    $("#modal").load("modal.html");
});

var gType;
var gKeyWord;

function gSelect(arg) {
    gType = arg.text;
    $("#type").text(gType);
}

function gSearch() {
    gKeyWord = $("#keyword").val();
    if (gKeyWord == "") {
        alert("请输入关键字");
        return;
    }

    switch (gType) {
        case "IP":
            location.href = "resultIP.html?ip=" + gKeyWord;
            break;
        case "域名":
            location.href = "resultDomain.html?domain=" + gKeyWord;
            break;
        case "SHA1":
            location.href = "resultFile.html?sha1=" + gKeyWord;
            break;
        case "SHA256":
            location.href = "resultFile.html?sha256=" + gKeyWord;
            break;
        default:
            alert("请选择查询类型");
            break;
    }
}

function gTree(url) {
    var diameter = $("#tab_content").width();

    var tree = d3.layout.tree()
        .size([360, diameter / 2 - 120])
        .separation(function (a, b) {
            return (a.parent == b.parent ? 1 : 2) / a.depth;
        });

    var diagonal = d3.svg.diagonal.radial()
        .projection(function (d) {
            return [d.y, d.x / 180 * Math.PI];
        });

    var svg = d3.select("#tree").append("svg")
        .attr("width", diameter)
        .attr("height", diameter)
        .append("g")
        .attr("transform", "translate(" + diameter / 2 + "," + diameter / 2 + ")");

    d3.json(url, function (error, root) {
        if (error) throw error;

        var nodes = tree.nodes(root),
            links = tree.links(nodes);

        var link = svg.selectAll(".link")
            .data(links)
            .enter().append("path")
            .attr("class", "link")
            .attr("d", diagonal);

        var node = svg.selectAll(".node")
            .data(nodes)
            .enter().append("g")
            .attr("class", "node")
            .attr("transform", function (d) {
                return "rotate(" + (d.x - 90) + ")translate(" + d.y + ")";
            });

        node.append("circle")
            .attr("r", 15);

        node.append("text")
            .attr("dy", ".31em")
            .attr("text-anchor", function (d) {
                return d.x < 180 ? "start" : "end";
            })
            .attr("transform", function (d) {
                return d.x < 180 ? "translate(20)" : "rotate(180)translate(-20)";
            })
            .text(function (d) {
                return d.name;
            });

        node.on("click", function (d) {
            $("#modalEntity").modal();
            $("#modalName").text(d.name);
        })
    });
}