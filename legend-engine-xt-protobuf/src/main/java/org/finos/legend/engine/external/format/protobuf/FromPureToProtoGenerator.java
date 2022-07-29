// Copyright 2021 Goldman Sachs
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.finos.legend.engine.external.format.protobuf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.collections.api.tuple.Pair;
import org.finos.legend.engine.external.format.protobuf.deprecated.generation.configuration.ProtobufGenerationConfig;
import org.finos.legend.engine.external.shared.format.generations.GenerationOutput;
import org.finos.legend.engine.language.pure.compiler.toPureGraph.PureModel;
import org.finos.legend.engine.language.pure.modelManager.ModelManager;
import org.finos.legend.engine.protocol.pure.v1.model.context.PureModelContextData;
import org.finos.legend.engine.protocol.pure.v1.model.context.PureModelContextText;
import org.finos.legend.engine.protocol.pure.v1.model.packageableElement.PackageableElement;
import org.finos.legend.engine.protocol.pure.v1.model.packageableElement.domain.Class;
import org.finos.legend.engine.shared.core.deployment.DeploymentMode;
import org.finos.legend.pure.generated.core_external_format_protobuf_deprecated;

public class FromPureToProtoGenerator
{
    private final ModelManager modelManager = new ModelManager(DeploymentMode.DEV);

    public List<GenerationOutput> generate(String pure)
    {
        PureModelContextText context = new PureModelContextText(pure);
        Pair<PureModelContextData, PureModel> pureModelPair = this.modelManager.loadModelAndData(context, null, null, null);

        PureModelContextData pureModelContextData = pureModelPair.getOne();
        ProtobufGenerationConfig protobufGenerationConfig = new ProtobufGenerationConfig();
        protobufGenerationConfig.scopeElements = pureModelContextData.getElements().stream()
                .filter(packageableElement -> packageableElement instanceof Class)
                .map(PackageableElement::getPath)
                .collect(Collectors.toList());

        PureModel pureModel = pureModelPair.getTwo();
        return core_external_format_protobuf_deprecated.Root_meta_external_format_protobuf_deprecated_generation_internal_transform_ProtobufConfig_1__GenerationOutput_MANY_(
                protobufGenerationConfig.transformToPure(pureModel), pureModel.getExecutionSupport())
                .collect(v -> new GenerationOutput(v._content(), v._fileName(), v._format())).toList();
    }
}
